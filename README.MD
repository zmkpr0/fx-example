## Assumptions:

- Received data is always complete and correct (ie. no missing columns, proper date format, position
  of column does not change)
- The code is meant to be used with a dependency injection framework like Spring. That's why there
  are placeholder "Provider classes" simulating a DI framework providing dependencies
- I assumed that "Publish the adjusted price to REST endpoint" means make prices available to be
  queried with an GET endpoint. I was wondering if it was supposed to mean "send the message to the
  existing endpoint", but there was a point in the task about "latest price", so I assumed that it's
  important to implement a data structure to handle this requirement

## Notes

- The code is uses the simplified hexagonal structure to allow potential separation of message,
  persistance and db modules
- The task didn'