# cidekiq

Bridge between Clojure<->Cidekiq

## Usage

```clojure
; Enqueue job
=> (co.noty.cidekiq/enqueue "h" "PingWorker" ["this", "test"])
1
; schedule job
=> space.yeo.cidekiq=> (space.yeo.cidekiq/schedule (+ 1496900760 60) "h" "PingWorker" ["this", "test"])
```

## Sponsor

This project was sponsor by (http://noty.im)[http://noty.im]
