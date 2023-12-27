package main

import (
	"context"
	"errors"
	"fmt"
	"io"
	"net/http"
	"sync"
	"time"
)

type Processor = func(ctx context.Context) (string, error)

func doWork(workType string, durationSecond int, result chan<- string) {
	fmt.Println("Work with - " + workType)
	time.Sleep(time.Duration(durationSecond) * time.Second)
	result <- "Result from " + workType
}

func doRequest(result chan<- string) {
	response, err := http.Get("http://localhost:3000/users")
	if err != nil {
		result <- err.Error()
	}

	bytes, err := io.ReadAll(response.Body)
	result <- string(bytes)
}

func goToRemoteServer(ctx context.Context) (result string, err error) {
	fmt.Println("RemoteServer is working...")
	ctxTimeout, cancel := context.WithTimeout(ctx, 3*time.Second)
	defer cancel()

	resultChannel := make(chan string)
	go doRequest(resultChannel)

	for {
		select {
		case <-ctxTimeout.Done():
			if errors.Is(ctxTimeout.Err(), context.DeadlineExceeded) {
				fmt.Println("RemoteServer timeout")
				return "", ctxTimeout.Err()
			}
			if errors.Is(ctx.Err(), context.Canceled) {
				fmt.Println("RemoteServer cancelled")
				return "", nil
			}
		case result = <-resultChannel:
			fmt.Println("RemoteServer done")
			return result, nil
		}
	}
}

func goToDataBase(ctx context.Context) (result string, err error) {
	fmt.Println("DataBase is working...")

	resultChannel := make(chan string)

	go doWork("DataBase", 4, resultChannel)

	for {
		select {
		case <-ctx.Done():
			if errors.Is(ctx.Err(), context.Canceled) {
				fmt.Println("DataBase cancelled")
				return "", nil
			}
		case result = <-resultChannel:
			fmt.Println("DataBase done")
			return result, nil
		}
	}
}

func goToDisk(ctx context.Context) (result string, err error) {
	fmt.Println("Disk is working...")

	resultChannel := make(chan string)

	go doWork("Disk", 8, resultChannel)

	for {
		select {
		case <-ctx.Done():
			if errors.Is(ctx.Err(), context.Canceled) {
				fmt.Println("Disk cancelled")
				return "", nil
			}
		case result = <-resultChannel:
			fmt.Println("Disk done")
			return result, nil
		}
	}
}

func processRequest(ctx context.Context, processors []Processor) {
	ctxWithCancel, cancel := context.WithCancel(ctx)

	var wg = &sync.WaitGroup{}
	wg.Add(len(processors))

	for _, processor := range processors {
		go func(p Processor) {
			result, err := p(ctxWithCancel)

			if err != nil {
				fmt.Println("Error - " + err.Error())
				cancel()
			} else if result != "" {
				fmt.Println("-> " + result)
			}

			wg.Done()
		}(processor)
	}

	wg.Wait()

	cancel()
}

func main() {
	ctx := context.Background()

	processors := []Processor{goToRemoteServer, goToDataBase, goToDisk}

	processRequest(ctx, processors)
}
