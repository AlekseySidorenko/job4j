package ru.job4j.foodstorage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Class ExpiringTimeCalculator | Implement FoodStorage [#852]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.09.2019
 */
public class ExpiringTimeCalculator {

    /**
     * Calculate common food expire time.
     * @param food Food.
     * @return Common expire time in days.
     */
    private long calculateBasicExpireTime(Food food) {
        DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date creationDate = null;
        Date expireDate = null;
        try {
            creationDate = format.parse(food.getCreateDate());
            expireDate = format.parse(food.getExpireDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long expireTime = expireDate.getTime() - creationDate.getTime();
        long expireTimeInDays = TimeUnit.DAYS.convert(expireTime, TimeUnit.MILLISECONDS);
        return expireTimeInDays;
    }

    /**
     * Calculate rest of expire time based on actual date.
     * @param food Food.
     * @param todayDate Actual date for calculating rest of the time to expire.
     * @return Rest of expire time in days.
     */
    private long calculateRestExpireTime(Food food, String todayDate) {
        DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date actualDate = null;
        Date expireDate = null;
        try {
            actualDate = format.parse(todayDate);
            expireDate = format.parse(food.getExpireDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long expireTimeLeft = expireDate.getTime() - actualDate.getTime();
        long expireTimeLeftInDays = TimeUnit.DAYS.convert(expireTimeLeft, TimeUnit.MILLISECONDS);
        return expireTimeLeftInDays;
    }

    /**
     * Calculate rest of expire time in percentage.
     * @param food Food.
     * @param todayDate Actual date for calculating rest of the time to expire.
     * @return Rest of expire time in percentage.
     */
    public double calculateActualExpiringTimeInPercents(Food food, String todayDate) {
        double basicExpireTime;
        double leftExpireTime;
        basicExpireTime = calculateBasicExpireTime(food);
        leftExpireTime = calculateRestExpireTime(food, todayDate);
        return ((leftExpireTime / (basicExpireTime / 100)));
    }
}