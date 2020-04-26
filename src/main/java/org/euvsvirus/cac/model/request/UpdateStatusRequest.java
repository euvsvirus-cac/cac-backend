package org.euvsvirus.cac.model.request;

/**
 * Simple boolean flag for now, but we might have a more complex structure
 * soon, lik 'Pause for x hours', 'end at 5pm', etc.
 */
public class UpdateStatusRequest {

    private boolean available;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
