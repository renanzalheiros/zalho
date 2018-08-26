# wafermessengerchallenge
How did I did:

### Communication Ui -> Controllers:
I used in this project a MVVM design pattern, combining it with databinding. I think it makes the code cleaner, more
testable (although I haven't done any testing - reasons later) and I'm already more familiar to this patern since I use it
in my work everyday.
Class CountryBindings contains all custom bindings I needed to do the challenge.

### Delegate Pattern on Service:
I'm more familiar to RxJava to do tasks like fetching Json data from webs API's. Working with Observables really makes it
easier to manage Restfull communication. As I could not use Rx on this project, I thought it would be easier to use delegate
pattern to work with asynchronous tasks. CountriesTaskDelegate it's an 
interface that communicates the service when synchronization has finished. When this occurs, the FetchCountriesDelegate
takes action, delegating the display of the synchronized data to the ui.

### RecyclerView:
I think it is way better than ListView for managing swipe actions.

### Swipe Actions:
This was very tricky. Reading the documentation, I was able to do all the work. ItemTouchHelper.SimpleCallback maked it easier.
My intention was to divide the swipe into three stages: invisible background, background with button appearing, full
background. For this, I used the convertToAbsoluteDirection, onChildDraw methods and created the
setOnTouchListenerToRecyclerView method.

Whenever onChildDraw is called, it detects if it is a swipe move and handles the cardview responsible for displaying the
country data. It is also responsible for registering the setOnTouchListenerToRecyclerView in the recycler view that will
detect swipe speed and cardview drives. Depending on the dX that the android sends me, I put barriers to the swipe back. If
the button width has been exceeded, the status of the swipe remains as the visible button. If it exceeded half of the
screen, full background.

Depending on the speed of the swipe (greater than 10 pixels per ms), the swipe back does not occur, and the line is immediately excluded.

To calculate speed, I used VelocityTracker. When it detected a MotionEvent.Action_Move event, it was starting to monitor the 
speed of the event. When you detected a MOtionEvent.Action_Up event, you stop calculating. onSelectedChanged was used for
when a card view had already been slipped and its button was visible, but another card was swiped and it is necessary to
manipulate the views so that only one is visible button

To communicate that a deletion action happened, I used the broadcast receiver, since I could not use RxJava as well. When a fast swipe or a delete button click is identified, delete broadcast is triggered. It communicates the
CountryListFragmentViewModel, responsible for keeping countriesList, that a deletion is necessary.

### Why not testing:

I wish I had tested, but I did not have enough time. I had to learn some new things for this test, joining with 8h daily work and college.
