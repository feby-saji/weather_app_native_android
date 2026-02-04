Weather Forecast App
A minimalist weather application built with Kotlin and Jetpack Compose that provides updates and a 5-day forecast using the OpenWeatherMap API.

🛠 Tech Stack & Dependencies
UI & Architecture
Jetpack Compose: For building a modern, declarative user interface.

Material 3: For styling and implementing the "Weather" theme components.

ViewModel: To manage UI state and business logic independently of the activity lifecycle.

Clean Architecture: Organized into data and presentation layers for better maintainability.

Networking & API
Retrofit: To handle HTTP requests to the OpenWeatherMap API.

Moshi / Gson: For converting JSON response data into Kotlin objects.

Coil: For loading and caching weather icons from the web.

OpenWeatherMap API: Fetches 5-day / 3-hour forecast data based on GPS coordinates.

Device Services
Google Play Services Location: Used to fetch the user's geographic coordinates (Latitude/Longitude).

Accompanist Permissions: For handling runtime location permissions within Compose.

