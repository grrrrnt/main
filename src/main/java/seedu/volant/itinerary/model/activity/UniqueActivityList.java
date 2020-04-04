package seedu.volant.itinerary.model.activity;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.volant.itinerary.exceptions.DuplicateActivityException;
import seedu.volant.itinerary.model.activity.exceptions.NotFoundException;


/**
 * A list of activities that enforces uniqueness between its elements and does not allow nulls.
 * A activity is considered unique by comparing using {@code Activity#isSameActivity}. As such, adding and updating of
 * activities uses Activity#isSameActivity for equality so as to ensure that the activity being added or updated is
 * unique in terms of identity in the UniqueActivityList. However, the removal of a activity uses
 * Activity#equals(Object) so as to ensure that the activity with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueActivityList implements Iterable<Activity> {

    private final ObservableList<Activity> internalList = FXCollections.observableArrayList();
    private final ObservableList<Activity> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent activity as the given argument.
    */


    public boolean contains(Activity toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if the list contains an activity with equivalent date and time as the argument.
     */
    public boolean containsDateTime(Activity toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equalsDateTime);
    }

    /**
     * Adds a activity to the list.
     * The activity must not already exist in the list.
     */

    public void add(Activity toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateActivityException();
        }
        internalList.add(toAdd);
    }

    public int getSize() {
        return internalList.size();
    }

    /*TODO uncomment out for v1.3
     /** Replaces the activity {@code target} in the list with {@code editedActivity}.
     * {@code target} must exist in the list.
     * The activity identity of {@code editedActivity} must not be the same as another existing activity in the list.

    public void setActivity(Activity target, Activity editedActivity) {
        requireAllNonNull(target, editedActivity);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new NotFoundException();
        }

        if (!target.isSameActivity(editedActivity) && contains(editedActivity)) {
            throw new DuplicateActivityException();
        }

        internalList.set(index, editedActivity);
    }
    */

    /**
    * Removes the equivalent activity from the list.
    * The activity must exist in the list.
    */

    public void remove(Activity toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new NotFoundException("No such Activity");
        }
    }

    /** Replaces the contents of this list with {@code activities}.
     *  {@code activities} must not contain duplicate activities.
     */

    public void setActivities(List<Activity> activities) {
        requireAllNonNull(activities);

        /* TODO: Implement methods to check for duplicate/clashing activities
        */

        if (!activitiesAreUnique(activities)) {
            throw new DuplicateActivityException();
        }

        internalList.setAll(activities);
    }

    public void setActivities(UniqueActivityList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the activity {@code target} in the list with {@code editedActivity}.
     * {@code target} must exist in the list.
     * The activity identity of {@code editedActivity} must not be the same as another existing activity in the list.
     */
    public void setActivity(Activity target, Activity editedActivity) {
        requireAllNonNull(target, editedActivity);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new NotFoundException();
        }

        if (!target.equals(editedActivity) && contains(editedActivity)) {
            throw new DuplicateActivityException();
        }

        internalList.set(index, editedActivity);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Activity> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Activity> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueActivityList // instanceof handles nulls
                && internalList.equals(((UniqueActivityList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }


    /** Returns true if {@code activities} contains only unique activities.
     *
     * @param activities list of activities
     * @return true if all activities in the list are unique.
     */

    private boolean activitiesAreUnique(List<Activity> activities) {
        for (int i = 0; i < activities.size() - 1; i++) {
            for (int j = i + 1; j < activities.size(); j++) {
                if (activities.get(i).equals(activities.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
