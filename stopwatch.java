let timer;
let startTime;
let isRunning = false;
let lapCounter = 1;

const display = document.getElementById('display');
const startBtn = document.getElementById('start');
const pauseBtn = document.getElementById('pause');
const resetBtn = document.getElementById('reset');
const lapBtn = document.getElementById('lap');
const lapsList = document.getElementById('laps');

function formatTime(milliseconds) {
    const totalSeconds = Math.floor(milliseconds / 1000);
    const minutes = Math.floor(totalSeconds / 60);
    const seconds = totalSeconds % 60;
    const formattedMinutes = String(minutes).padStart(2, '0');
    const formattedSeconds = String(seconds).padStart(2, '0');
    return `${formattedMinutes}:${formattedSeconds}`;
}

function updateDisplay() {
    const currentTime = Date.now();
    const elapsedTime = currentTime - startTime;
    display.textContent = formatTime(elapsedTime);
    document.body.style.backgroundColor = '#55286F'; //Set background color
}

function startTimer() {
    if (!isRunning) {
        startTime = Date.now();
        timer = setInterval(updateDisplay, 1000);
        isRunning = true;
        startBtn.textContent = 'running';
    } else {
        clearInterval(timer);
        isRunning = false;
        startBtn.textContent = 'Resume';
    }
}

function pauseTimer() {
    clearInterval(timer);
    isRunning = false;
    startBtn.textContent = 'Resume';
}

function resetTimer() {
    clearInterval(timer);
    isRunning = false;
    display.textContent = '00:00:00';
    startBtn.textContent = 'Start';
    lapsList.innerHTML = '';
    lapCounter = 1;
}

function lapTime() {
    const lapTime = formatTime(Date.now() - startTime);
    const lapItem = document.createElement('li');
    lapItem.textContent = `Lap ${lapCounter}: ${lapTime}`;
    lapsList.prepend(lapItem);
    lapCounter++;
}

startBtn.addEventListener('click', startTimer);
pauseBtn.addEventListener('click', pauseTimer);
resetBtn.addEventListener('click', resetTimer);
lapBtn.addEventListener('click', lapTime);

// Set initial background color
document.body.style.backgroundColor = '#FF8484';
