package xyz.bringoff.yalantistask1;

import xyz.bringoff.yalantistask1.data.RequestDataSource;
import xyz.bringoff.yalantistask1.data.RequestRepository;

// RequestRepository.java@57
// onDataOnAvailable - shoud be called onDataNotAvailable?  
// Missing break in for loop, therefore onRequestLoaded can be called multiple times
// Why not use appropriate data structure (*Map) in the first place instead of a for loop?
// onDataOnAvailable is always called regardless of whether request was found or not

// RequestRepository.java@25
// Method needn’t be static

// RequestRepository.java@59 should be in `if` body

// DetailsActivity.java@125:
// No break/return after case

// ImagesRecyclerAdapter.java@34
// mUrls can be null, good idea to check if it is and return 0 count. (if you think it can’t be null, at least add @NonNull annotation)

// RequestListFragment@66
// Duplicate notifyDataChanges

// FabHidingBehaviour
// Suppress unused warning for class

// RequestDataSource
// Should be called *Interface
// Logic is a bit complicated

// UI:
// Fab disappears and appears randomly on scroll (WAITING tab)
// I see UUIDs instead of statuses and descriptions


// Nice stuff:
// vector drawable
// Instance holder
// supportVersion constant in gradle



public class Injection {

    public static RequestDataSource provideRequestDataSource() {
        return RequestRepository.getInstance();
    }
}
