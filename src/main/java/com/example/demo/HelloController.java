package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;


@RestController
public class HelloController {
    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }
    
@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String returnHtml() {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Service Status</title>
                <style>
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        background-color: #f4f7f6;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
                    }
                    .card {
                        background: white;
                        padding: 2rem;
                        border-radius: 12px;
                        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
                        text-align: center;
                        max-width: 400px;
                    }
                    .status-icon {
                        font-size: 3rem;
                        color: #2ecc71;
                        margin-bottom: 1rem;
                    }
                    h1 { color: #2c3e50; margin: 0; }
                    p { color: #7f8c8d; line-height: 1.6; }
                    .btn {
                        display: inline-block;
                        margin-top: 1.5rem;
                        padding: 10px 20px;
                        background-color: #3498db;
                        color: white;
                        text-decoration: none;
                        border-radius: 5px;
                        transition: background 0.3s;
                    }
                    .btn:hover { background-color: #2980b9; }
                </style>
            </head>
            <body>
                <div class="card">
                    <div class="status-icon">✔</div>
                    <h1>System Online Version 2</h1>
                    <p>The backend service is running smoothly. All microservices are currently reachable and stable.</p>
                    <a href="/save-user" class="btn">Test Database</a>
                </div>
            </body>
            </html>
            """;
    }


    @PostMapping("/save-user")
    @ResponseBody
    public String saveUser(
        @RequestParam String name,
        @RequestParam String email
    ) {

        userService.saveUser(name, email);
        return "User saved successfully";
    }

    @GetMapping(value = "/save-user", produces = MediaType.TEXT_HTML_VALUE)
    public String saveUserForm() {
        return """
            <!DOCTYPE html>
            <html lang=\"en\">
            <head>
                <meta charset=\"UTF-8\">
                <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">
                <title>Save User</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f6f8fa;
                        margin: 0;
                        padding: 40px;
                    }
                    .card {
                        max-width: 480px;
                        margin: 0 auto;
                        background: #fff;
                        border-radius: 10px;
                        box-shadow: 0 6px 20px rgba(0, 0, 0, 0.08);
                        padding: 24px;
                    }
                    h1 {
                        margin: 0 0 16px;
                        font-size: 22px;
                        color: #1f2328;
                    }
                    label {
                        display: block;
                        margin: 12px 0 6px;
                        font-size: 14px;
                        color: #57606a;
                    }
                    input {
                        width: 100%;
                        padding: 10px 12px;
                        border: 1px solid #d0d7de;
                        border-radius: 6px;
                        font-size: 14px;
                    }
                    button {
                        margin-top: 16px;
                        width: 100%;
                        padding: 10px 12px;
                        background: #0969da;
                        color: #fff;
                        border: none;
                        border-radius: 6px;
                        font-size: 15px;
                        cursor: pointer;
                    }
                    button:hover {
                        background: #0550ae;
                    }
                </style>
            </head>
            <body>
                <div class=\"card\">
                    <h1>Save User</h1>
                    <form method=\"post\" action=\"/save-user\">
                        <label for=\"name\">Name</label>
                        <input id=\"name\" name=\"name\" type=\"text\" required>

                        <label for=\"email\">Email</label>
                        <input id=\"email\" name=\"email\" type=\"email\" required>

                        <button type=\"submit\">Save</button>
                    </form>
                </div>
            </body>
            </html>
            """;
    }
}
