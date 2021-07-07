def freespace(String path) {
	env.return_val = powershell script: "C:\\Temp\\disk_usage.ps1 $path", returnStdout: true  // execute powershell script and pass the workspace path
	echo "Available space for Jenkins workspace: ${env.return_val.trim()} GB"
}
return this