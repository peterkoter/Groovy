import static Activity.*
import static Gender.*

enum Gender {
    MALE, FEMALE
}

enum Activity {
    NONE, LIGHT, MODERATE, HEAVY, VERY_HEAVY
}

/**
 * This is a Basal Metabolic Rate Calculator based on Harris Benedict's formula
 *
 * @param gender Male or Female
 * @param weight in kg
 * @param height in cm
 * @param age in years
 * @return the persons Basal Metabolic Rate
 */

def basalMetabolicRate(Gender gender, Number weight, Number height, Number age) {
    def offset
    def weightScale
    def heightScale
    def ageScale

    switch (gender) {
        case MALE:
            offset = 88.362
            weightScale = 13.397
            heightScale = 4.799
            ageScale = 5.677
            break
        case FEMALE:
            offset = 447.593
            weightScale = 9.247
            heightScale = 3.098
            ageScale = 4.330
            break
    }
    offset + (weightScale * weight) + (heightScale * height) - (ageScale * age)
}

def dailyCalorieIntake(Activity activity, Number basalMetabolicRate) {
    switch (activity) {
        case NONE: return basalMetabolicRate * 1.2          // Little to no exercise
        case LIGHT: return basalMetabolicRate * 1.375       // Light exercise (1–3 days per week)
        case MODERATE: return basalMetabolicRate * 1.55     // Moderate exercise (3–5 days per week)
        case HEAVY: return basalMetabolicRate * 1.725       // Heavy exercise (6–7 days per week)
        case VERY_HEAVY: return basalMetabolicRate * 1.9    // Very heavy exercise (twice per day, extra heavy workouts)
    }
}

def somebodysBmr = basalMetabolicRate(FEMALE, 100, 170, 27)
def recommendedDailyCalorieIntake = dailyCalorieIntake(VERY_HEAVY, somebodysBmr)
println "Your BMR: ${somebodysBmr}, recommended Daily Calorie Intake: ${recommendedDailyCalorieIntake}"
