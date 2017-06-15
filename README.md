# cidekiq

Bridge between Clojure ⟷ Sidekiq

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

A Redis connection is handled for you automatically by reading the env var
`REDIS_URI` to find redis address. If not found, it uses the default
`redis://127.0.0.1:6379` which is for a local Redis instance on the
default port.

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
## License

Distributed under the [Eclipse Public License v1.0](https://github.com/yeospace/cidekiq/blob/master/LICENSE)

Copyright © Vinh Quốc Nguyễn 2017

## Sponsor

This project was sponsored by [http://noty.im](http://noty.im)
