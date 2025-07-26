local file = io.open("/tmp/script.json", "r")
wrk.method = "POST"
wrk.body   = file:read("*a")
wrk.headers["Content-Type"] = "application/json"
file:close()