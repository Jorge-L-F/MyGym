// ref calories 1h 70kg 25y
var maxRun = 1176;
var minRun = 588;
var maxCyc = 735;
var minCyc = 441;
var maxRow = 625;
var minRow = 257;
var maxSkip = 882;
var minSkip = 588;
var maxLift = 435;
var minLift = 221;
var maxBoxing = 662;
var minBoxing = 448;
var maxYoga = 232;
var minYoga = 154;
var maxPilates = 208;
var minPilates = 129;
var maxCrossfit = 419;
var minCrossfit = 231;
var maxZumba = 415;
var minZumba = 215;
var maxSwimming = 710;
var minSwimming = 432;
var refAge = 25;
var refDuration = 60;
var refHeight = 180;
var refWeight = 70;

function generateData(gender, age, height, weight, typeClass, duration) {
    let calories;
    let heartRate = 0; //TODO average heart rate
    switch (typeClass) {
        case "running":
            if (gender == "male") {
                calories =
                    (Math.floor(Math.random() * (maxRun - minRun + 1)) +
                        minRun) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight);
            } else {
                calories =
                    (Math.floor(Math.random() * (maxRun - minRun + 1)) +
                        minRun) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight) *
                    0.9;
            }
            break;
        case "cycling":
            if (gender == "male") {
                calories =
                    (Math.floor(Math.random() * (maxCyc - minCyc + 1)) +
                        minCyc) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight);
            } else {
                calories =
                    (Math.floor(Math.random() * (maxCyc - minCyc + 1)) +
                        minCyc) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight) *
                    0.9;
            }
            break;
        case "rowing":
            if (gender == "male") {
                calories =
                    (Math.floor(Math.random() * (maxRow - minRow + 1)) +
                        minRow) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight);
            } else {
                calories =
                    (Math.floor(Math.random() * (maxRow - minRow + 1)) +
                        minRow) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight) *
                    0.9;
            }
            break;
        case "skippingRope":
            if (gender == "male") {
                calories =
                    (Math.floor(Math.random() * (maxSkip - minSkip + 1)) +
                        minSkip) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight);
            } else {
                calories =
                    (Math.floor(Math.random() * (maxSkip - minSkip + 1)) +
                        minSkip) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight) *
                    0.9;
            }
            break;
        case "weightlifting":
            if (gender == "male") {
                calories =
                    (Math.floor(Math.random() * (maxLift - minLift + 1)) +
                        minLift) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight);
            } else {
                calories =
                    (Math.floor(Math.random() * (maxLift - minLift + 1)) +
                        minLift) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight) *
                    0.9;
            }
            break;
        case "boxing":
            if (gender == "male") {
                calories =
                    (Math.floor(Math.random() * (maxBoxing - minBoxing + 1)) +
                        minBoxing) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight);
            } else {
                calories =
                    (Math.floor(Math.random() * (maxBoxing - minBoxing + 1)) +
                        minBoxing) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight) *
                    0.9;
            }
            break;
        case "yoga":
            if (gender == "male") {
                calories =
                    (Math.floor(Math.random() * (maxYoga - minYoga + 1)) +
                        minYoga) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight);
            } else {
                calories =
                    (Math.floor(Math.random() * (maxYoga - minYoga + 1)) +
                        minYoga) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight) *
                    0.9;
            }
            break;
        case "pilates":
            if (gender == "male") {
                calories =
                    (Math.floor(Math.random() * (maxPilates - minPilates + 1)) +
                        minPilates) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight);
            } else {
                calories =
                    (Math.floor(Math.random() * (maxPilates - minPilates + 1)) +
                        minPilates) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight) *
                    0.9;
            }
            break;
        case "crossfit":
            if (gender == "male") {
                calories =
                    (Math.floor(
                        Math.random() * (maxCrossfit - minCrossfit + 1)
                    ) +
                        minCrossfit) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight);
            } else {
                calories =
                    (Math.floor(
                        Math.random() * (maxCrossfit - minCrossfit + 1)
                    ) +
                        minCrossfit) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight) *
                    0.9;
            }
            break;
        case "zumba":
            if (gender == "male") {
                calories =
                    (Math.floor(Math.random() * (maxZumba - minZumba + 1)) +
                        minZumba) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight);
            } else {
                calories =
                    (Math.floor(Math.random() * (maxZumba - minZumba + 1)) +
                        minZumba) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight) *
                    0.9;
            }
            break;
        case "swimming":
            if (gender == "male") {
                calories =
                    (Math.floor(
                        Math.random() * (maxSwimming - minSwimming + 1)
                    ) +
                        minSwimming) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight);
            } else {
                calories =
                    (Math.floor(
                        Math.random() * (maxSwimming - minSwimming + 1)
                    ) +
                        minSwimming) *
                    (duration / refDuration) *
                    (refAge / age) *
                    (height / refHeight) *
                    (weight / refWeight) *
                    0.9;
            }
            break;
        default:
    }
    return { calories: calories, heartRate: heartRate };
}

export default generateData;
