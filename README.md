# order domain spike


## created order

#### create normal order
```json
curl -XPOST "http://localhost:20008/monitor/v1/createOrder"  -H "Content-Type:application/json" -d '{
	"fullPrice": 100,
	"payment": 200,
	"orderStatus":  1,
	"entry": 2,
	"orderNo": 3,
	"customerId": 123456677823,
	"addressId": 1,
	"payNo": "sx293842834637",
	"payWay": "wexin",
	"dispatchTime": "2019-12-11"
}'
```

#### create group order

```json
curl -XPOST "http://localhost:20008/monitor/v1/createOrder"  -H "Content-Type:application/json" -d '{
	"fullPrice": 100,
	"payment": 200,
	"orderStatus":  1,
	"entry": 2,
	"orderNo": 3,
	"customerId": 123456677823,
	"addressId": 1,
	"payNo": "sx293842834637",
	"payWay": "wexin",
	"dispatchTime": "2019-12-11",
	"capacity": 100,
	"participants": [89,100,38]
}'
```
