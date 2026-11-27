1..50 | ForEach-Object {
    curl.exe -s http://localhost:8080/observabilidade/teste
    Start-Sleep -Milliseconds 500

    if ($_ % 5 -eq 0) {
        curl.exe -s http://localhost:8080/observabilidade/erro
    }
}