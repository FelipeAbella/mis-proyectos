install.packages("readxl")
library(readxl)

data=read_excel('C:\\Users\\abell\\Desktop\\BaseDatos.xlsx')

head(data)

variable1 <- data$CASOS

B <- 10000
medias_bootstrap <- numeric(B)

set.seed(123)
for(i in 1:B) {
  muestra_bootstrap <- sample(variable1, size=length(variable1), replace=TRUE)
  medias_bootstrap[i] <- mean(muestra_bootstrap)
}

intervalo_confianza_media <- quantile(medias_bootstrap, probs = c(0.1, 0.9))
print(intervalo_confianza_media)

mean_value <- mean(variable1)
sd_value <- sd(variable1)
n <- length(variable1)

error_margin <- qt(0.9, df=n-1) * (sd_value / sqrt(n))
ci_t <- c(mean_value - error_margin, mean_value + error_margin)
print(ci_t)

variable2 <- data$MUERTES

B2 <- 10000
desviaciones_bootstrap <- numeric(B2)

set.seed(123)
for(i in 1:B) {
  muestra_bootstrap2 <- sample(variable2, size=length(variable2), replace=TRUE)
  desviaciones_bootstrap[i] <- mean(muestra_bootstrap2)
}

intervalo_confianza_sd <- quantile(desviaciones_bootstrap, probs = c(0.075, 0.925))
print(intervalo_confianza_sd)

sd_value2 <- sd(variable2)
n2 <- length(variable2)

alpha <- 0.15
lower_bound <- qchisq(alpha/2, df=n2-1)*(sd_value2^2)/(n2-1)
upper_bound <- qchisq(1-alpha/2, df=n2-1)*(sd_value2^2)/(n2-1)
ci_chi_sq <- sqrt(c(lower_bound, upper_bound))

print(ci_chi_sq)









hist(variable1, main = "Histograma de CASOS", xlab = "CASOS", col = "lightblue", border = "black")
hist(variable2, main = "Histograma de MUERTES", xlab = "MUERTES", col = "lightcoral", border = "black")
