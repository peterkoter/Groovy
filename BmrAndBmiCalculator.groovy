enum Gender {
    MEN, WOMEN
}

enum Activity {
    NONE, LIGHT, MODERATE, HEAVY, VERY_HEAVY
}

def harrisBenedictBmr(Gender gender, Number weight, Number height, Number age) {
    switch (gender) {
        case Gender.MEN: 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age)
            break
        case Gender.WOMEN: 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age)
            break
    }
}

def dailyCalorieIntake(Activity activity, Number harrisBenedictBmr) {
    switch (activity) {
        case Activity.NONE: harrisBenedictBmr * 1.2
            break
        case Activity.LIGHT: harrisBenedictBmr * 1.375
            break
        case Activity.MODERATE: harrisBenedictBmr * 1.55
            break
        case Activity.HEAVY: harrisBenedictBmr * 1.725
            break
        default: harrisBenedictBmr * 1.9 // initially here was: case Activity.VERY_HEAVY: ... but I just
    // wanted to try if it works this way.

    }
}

def bodyMassIndex(Number weight, Number height) {
    weight / (height**2)
}

def bmiResult(bodyMassIndex) {
    if (bodyMassIndex < 15) "Very severly underweight"
    else if (bodyMassIndex < 16) "Severly underweight"
    else if (bodyMassIndex < 18.5) "Underweight"
    else if (bodyMassIndex < 25) "Normal (healthy weight)"
    else if (bodyMassIndex < 30) "Overweight"
    else "Obese + But now I understand the if statements"
}

def somebodysBmi = bodyMassIndex(100, 1.7)
def somebodysBmr = harrisBenedictBmr(Gender.MEN, 100, 170, 27)
def recommendedDailyCalorieIntake = dailyCalorieIntake(Activity.VERY_HEAVY, somebodysBmr)
println "You are ${bmiResult(somebodysBmi)}, BMI: ${somebodysBmi}, BMR: ${somebodysBmr}, Recommended Daily Calorie Intake: ${recommendedDailyCalorieIntake}"

