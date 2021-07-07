param(
	[string]$jenkins_path
)

$drive_letter = Split-Path -Path $jenkins_path -Qualifier  # get the drive letter from a workspace path

$free_space = Get-WmiObject Win32_LogicalDisk -Filter "DeviceID='$drive_letter'" | Select-Object FreeSpace  # get free space 

[math]::round($free_space.FreeSpace/1GB,2)  # convert free space to GB and round it