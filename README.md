# Auction Coding Challenge

The application reports auctions winners based on site and bidder configurations loaded from config.json and auctions loaded from input.json . The rules for reporitng winners are speceified in https://gist.github.com/marks-sortable/4771fed136dc70e77853d13d93e23061




## Inputs

config file format


```json
{
    "sites": [
        {
            "name": "houseofcheese.com",
            "bidders": ["AUCT", "BIDD"],
            "floor": 32
        }
    ],
    "bidders": [
        {
            "name": "AUCT",
            "adjustment": -0.0625
        },
        {
            "name": "BIDD",
            "adjustment": 0
        }
    ]
}
```
Input file format

```json
[
    {
        "site": "houseofcheese.com",
        "units": ["banner", "sidebar"],
        "bids": [
            {
                "bidder": "AUCT",
                "unit": "banner",
                "bid": 35
            },
            {
                "bidder": "BIDD",
                "unit": "sidebar",
                "bid": 60
            },
            {
                "bidder": "AUCT",
                "unit": "sidebar",
                "bid": 55
            }
        ]
    }
]
```


## build and execution

```bash
$ docker build -t challenge .
$ docker run -i -v /path/to/challenge/config.json:/auction/config.json challenge < /path/to/challenge/input.json
