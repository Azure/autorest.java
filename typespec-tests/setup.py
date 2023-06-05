import subprocess
import sys

# A python wrapper to call the PowerShell Setup.ps1 script
p = subprocess.Popen(["powershell.exe", ".\Setup.ps1"], stdout=sys.stdout)
p.communicate()
print("Setup complete")
