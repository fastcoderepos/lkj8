Function Test-CommandExists
{
 Param ($command)

 $oldPreference = $ErrorActionPreference

 $ErrorActionPreference = ‘stop’

 try {if(Get-Command $command){RETURN $true}}

 Catch {Write-Host “$command does not exist”; RETURN $false}

 Finally {$ErrorActionPreference=$oldPreference}

} #end function test-CommandExists


Get-PSProvider -PSProvider Environment

$env:COMPOSE_CONVERT_WINDOWS_PATHS=1

#check that the Docker compose file exists in the current directory

$filepath = $PSScriptRoot + "\docker-compose.yml"

$exists = Test-Path $filepath -PathType Leaf  

#Write-Host $exists 

if($exists -eq $False) {

    Write-Host "The docker compose file does not exist in the current directory!"
	exit
}

#Get the client and server directory names

$CLIENT_DIR = Get-ChildItem -Filter '*Client' | Select-Object -ExpandProperty Name

Write-Host $CLIENT_DIR

$SPLITARRAY= $CLIENT_DIR.split([string[]]"Client", [StringSplitOptions]::None)

$SERVER_DIR=$SPLITARRAY[0]

if (Test-Path ".\.env") {
  Remove-Item ".\.env"
}

# Create a .env file

$OFS = "`r`n"
$msg = "CLIENT_DIR=" + $CLIENT_DIR + $OFS

Add-Content -Path ".\.env" -Value $msg

$msg = "SERVER_DIR=" + $SERVER_DIR + $OFS

Add-Content -Path ".\.env" -Value $msg

$msg = "WAR_FILE=" + $SERVER_DIR + "-0.0.1-SNAPSHOT.war" + $OFS

Add-Content -Path ".\.env" -Value $msg

# Now run docker-compose.yml

# Check whether command exists and if so run it

$program = "docker-compose"

$cmd = "docker-compose up"

If(Test-CommandExists $program) {

#Write-Host $PWD

Set-Location -Path $PSScriptRoot

iex $cmd

}

else {

 Write-Host "docker-compose command is not available on your path environment variable. Please ensure you have docker-compose installed and it's added to your environment path variable."

}
