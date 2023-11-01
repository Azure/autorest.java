if (Test-Path node_modules) {
    Remove-Item node_modules -Recurse -Force
}

if (Test-Path package-lock.json) {
    Remove-Item package-lock.json
}

if (Test-Path openai-sdk) {
    Remove-Item openai-sdk
}

npm install

tsp compile main.tsp
