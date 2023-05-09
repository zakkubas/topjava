package ru.javaops.topjava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava.error.DataConflictException;
import ru.javaops.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MealRepository extends BaseRepository<Meal> {

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Meal> getAll(int userId);

    @Query("SELECT m from Meal m WHERE m.user.id=:userId AND m.dateTime >= :startDate AND m.dateTime < :endDate ORDER BY m.dateTime DESC")
    List<Meal> getBetweenHalfOpen(int userId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT m FROM Meal m WHERE m.id = :id and m.user.id = :userId")
    Optional<Meal> get(int userId, int id);

    @Query("SELECT m FROM Meal m JOIN FETCH m.user WHERE m.id = :id and m.user.id = :userId")
    Optional<Meal> getWithUser(int id, int userId);

    default Meal getExistedOrBelonged(int userId, int id) {
        return get(userId, id).orElseThrow(
                () -> new DataConflictException("Meal id=" + id + "   is not exist or doesn't belong to User id=" + userId));
    }
}