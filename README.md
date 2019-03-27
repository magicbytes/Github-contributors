# Babylon Test - one of the many possible solutions...
This project resolves the test by using **MVP** architecture. Where the `Presenter` is the "glue" between `Model` and `View`. To make the project modular and loose coupled, we rely on Dependency Injection, and another win being getting rid of dependency on the `Context`, as we can't have unit tests for business logic which relays on it.

There is a unit test class to show how easily we can create unit tests and test the business logic, skipping the UI dependency by using the MVP. The unit test is created using **JUnit4** in combination with **Mockito** for mocking the dependency and checking the method calls and it's parameters.

To make the network calls we rely on **Retrofit** and for deserialisation of the response, since it's in JSON format, we are using **GSON** library from Google. In addition to these libraries, we are using **Picasso** for easy and elegant way to download images straight into our `ImageView`s.

### Animations
We are using animations when we are presenting the list of items inside a `RecyclerView`, the animation being a slide from the left to right when the item list become visible. Another animation called _shared element animation_, it's used when the user selects a list item, and the details screen is opening, this way we are having a nice transition from list item to details.

### UI Patterns
In this project we are using Swipe to Refresh pattern, so in case if we want to refresh we can just pull down, and we will see a spinning wheel showing the progress until the items will be refreshed. 

It's worth mentioning that we have used `Snackbar` class for showing a toast message when there is an error, and this snackbar has an action for retry. 


### Improvements
This is the first version of this solution, and there can be multiple improvements done, some of them are:
1. Optimise the current workflow, where when we show the details of a post, we make 2 network calls, which is not optimal. 
2. Save the posts in a local way so we can extract them later, maybe using Room library
3. Use master-detail pattern by using `Fragments` so we can have nice interface for Tablets and Phones
4. UI can be improved.