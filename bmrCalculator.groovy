import static Activity.NONE

enum Gender {
    MEN, WOMEN
}

enum Activity {
    NONE, LIGHT, MODERATE, HEAVY, VERY_HEAVY
}

/**
 * This is a Basal Metabolic Rate Calculator based on Harris Benedict's formula
 *
 * @param gender Men or Women
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
        case Gender.MEN:
            offset = 88.362
            weightScale = 13.397
            heightScale = 4.799
            ageScale = 5.677
            break
        case Gender.WOMEN:
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
        case NONE:                              // Little to no exercise
            return basalMetabolicRate * 1.2
        case Activity.LIGHT:                    // Light exercise (1–3 days per week)
            return basalMetabolicRate * 1.375
        case Activity.MODERATE:                 // Moderate exercise (3–5 days per week)
            return basalMetabolicRate * 1.55
        case Activity.HEAVY:                    // Heavy exercise (6–7 days per week)
            return basalMetabolicRate * 1.725
        case Activity.VERY_HEAVY:               // Very heavy exercise (twice per day, extra heavy workouts)
            return basalMetabolicRate * 1.9
    }
}

/**
 * This is a Body Mass Index Calculator
 *
 * @param weight in kg
 * @param height in meter
 * @return the persons Body Mass Index
 */

def somebodysBmr = basalMetabolicRate(Gender.MEN, 100, 170, 27)
def recommendedDailyCalorieIntake = dailyCalorieIntake(Activity.VERY_HEAVY, somebodysBmr)
println "Your BMR: ${somebodysBmr}, recommended Daily Calorie Intake: ${recommendedDailyCalorieIntake}"
