- **Research and implement a CLI-based interface**
    - Instead of building a GUI for the UI layer, consider implementing a CLI that allows users to interact with the backend through the console.
    - This is an important task and should be researched thoroughly before implementation.

- **Implement Game services**
    - The Game side has not been covered yet.
    - Implement the required Game services.
    - Before starting the service implementation, review and update the Game entity if necessary.

- **Implement Movie services**
    - The Movie side has not been covered yet.
    - Implement the required Movie services.
    - Before starting the service implementation, review and update the Movie entity if necessary.

- **Define default book sorting**
    - Books should be sorted by publication year by default.
    - Older books should have higher priority, meaning the default sorting order should be ascending by publication year.
    - This default sorting behavior should also be applied when books are retrieved through search or filtering operations, unless another sorting option is explicitly requested.