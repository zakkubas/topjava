package ru.javawebinar.topjava.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mealsTo", MealsUtil.filteredByStreams(MealsUtil.initializeMeals(),
                LocalTime.of(0, 0), LocalTime.of(23, 59), 2000));
        getServletContext().getRequestDispatcher("/meals.jsp").forward(req, resp);
        log.debug("redirect to meals");
    }
}
