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
    switch (gender) {
        case MALE:
            return formula(88.362, (13.397 * weight), (4.799 * height), (5.677 * age))
        case FEMALE:
            return formula(447.593, (9.247 * weight), (3.098 * height), (4.330 * age))
    }
}

def formula(Number offset, Number scaledWeight, Number scaledHeight, Number scaledAge) {
    offset + scaledWeight + scaledHeight - scaledAge
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
