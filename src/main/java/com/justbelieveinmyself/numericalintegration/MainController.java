package com.justbelieveinmyself.numericalintegration;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.function.Function;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainController {
    @FXML
    private TextField aText;
    @FXML
    private TextField bText;
    @FXML
    private TextField eText;
    @FXML
    private TextField nText;
    @FXML
    private TextField funcText;
    @FXML
    private Label resultLabelSimpson;
    @FXML
    private Label resultLabelRect;

    @FXML
    protected void calculate() {
        double e, a, b;
        e = Double.parseDouble(eText.getText());
        a = Double.parseDouble(aText.getText());
        b = Double.parseDouble(bText.getText());


        String func_str = funcText.getText();
        Expression expression = new ExpressionBuilder(func_str)
                .variables("x")
                .build();

        Function<Double, Double> func = x -> {
            try {
                expression.setVariable("x", x);
                return expression.evaluate();
            } catch (ArithmeticException ex) {
                return Double.NaN;
            }
        };

        int n = 4;

        double simp = calculateSimpson(a, b, n, func);

        while (true) {
            System.out.println(simp);
            n *= 2;
            double simp2 = calculateSimpson(a, b, n, func);
            if (Math.abs((simp2 - simp) / 15) <= e) {
                break;
            }
            simp = simp2;

        }

        double rect = rectangleMethodIntegration(a, b, n, func);
        nText.setText(String.valueOf(n));
        resultLabelSimpson.setText("Симпсон: " + simp);
        resultLabelRect.setText("Прямоугольник: " + rect);
    }

    public static double calculateSimpson(double a, double b, int n, Function<Double, Double> func) {
        double h = (b - a) / n;
        double integral = func.apply(a) + func.apply(b);
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 2 == 0) {
                integral += 2 * func.apply(x);
            } else {
                integral += 4 * func.apply(x);
            }
        }
        integral *= h / 3;
        return integral;
    }

    public static double rectangleMethodIntegration(double a, double b, int n, Function<Double, Double> f) {
        double h = (b - a) / n;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            double x = a + i * h;
            sum += f.apply(x);
        }
        return h * sum;
    }

}