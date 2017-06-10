# cidekiq

Bridge between Clojure<->Cidekiq

## Getting started

Add the necessary dependency to your project:

```clojure
[space.yeo/cidekiq "0.1.0"]
```

And setup your namespace imports:

```
(ns my-app
  (:require [space.yeo/cidekiq :as cidekiq]))
```

## Connection

Connection is handle for you automatically by reading env var
`REDIS_URI` to find redis address. If not found, it uses default
`redis://127.0.0.1:6379` means your local

## Usage

```clojure
; Enqueue job
=> (space.yeo.cidekiq/enqueue "h" "SidekiqWorker" ["this", "test"])
1
; schedule job
=> (space.yeo.cidekiq/schedule (+ 60 (float (/ (System/currentTimeMillis)
1000))) "h" "SidekiqWorker" ["this", "test"])
1
```

## Sponsor

This project was sponsor by [http://noty.im](http://noty.im)
