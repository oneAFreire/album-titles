# Justification of Choices Made

## Tech Stack

### Data Persistence
- **Choice**: Room Database
- **Justification**: Room is simpler to use compared to the traditional SQLite, it provides a good abstraction layer over SQLite and integrates seamlessly with PagingSource which helps a lot in the UI lists.

### Networking
- **Choice**: Retrofit
- **Justification**: It is simple to use and allows an easy conversion of JSON responses into data classes.

### UI
- **Choice**: Jetpack Compose with ViewModels
- **Justification**: Jetpack compose is the official standard in today's Android native development. It is Kotlin and it's faster to develop, besides, it helps to keep the code more organized and cleaner. Using ViewModels helps handle configuration changes since the ViewModels can survive configuration changes like screen rotations without losing the data.

### Dependency injection
- **Choice**: Hilt
- **Justification**: It is the official DI solution for Android. It generates code in compile time, making the app faster and more efficient, special in large-scale apps. It has good integration with Android components like ViewModels.

## Architecture

```
app/
core/
│── data
│── database
│── designsystem
│── domain
│── model
│── network
feature/
│── albums
```

For the architecture, a modular architecture was used. It helps in build times and scalability since every module has a different responsibility. The modules are isolated from each other, providing mostly abstractions and not implementations.

### app
Is the main Android app

### core
Is the folder where the core modules are organized

-**data** This module is responsible for providing the data to the UseCases, managing the data sources between different data sources (database, network, etc), and mapping it to the correct data model used in the app.\
-**database**: This module provides offline storage in a local database.\
-**designsystem**: This module provides the UI components that can be shared between features and the app theme.\
-**domain**: This module is where the business logic is located, organized in UseCases.\
-**model**: This module provides the base data classes (models) used in the main app.\
-**network**: This module provides the methods to fetch data from the internet.\

### feature
This is where the app's UI features are organized and separated into modules (each model has its UI and ViewModel.
