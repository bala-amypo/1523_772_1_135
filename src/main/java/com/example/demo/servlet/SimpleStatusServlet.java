package com.example.demo.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/simple-status")
public class SimpleStatusServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("OK");
    }
     
}