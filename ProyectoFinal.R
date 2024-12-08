install.packages("readxl")
library(readxl)

baseDeDatosEstudioCaso <- read_excel('C:\\Users\\abell\\Desktop\\baseDeDatosEstudioCaso.xlsx')

install.packages("dplyr")
library(dplyr)

NbaseDeDatosEstudioCaso <- baseDeDatosEstudioCaso %>% select(Año, Casos, Poblacion, Tasa)
NbaseDeDatosEstudioCaso$Año <- gsub("[^0-9.]", "", NbaseDeDatosEstudioCaso$Año)
NbaseDeDatosEstudioCaso$Año <- as.numeric(NbaseDeDatosEstudioCaso$Año)
NbaseDeDatosEstudioCaso$Poblacion <- gsub("[^0-9.]", "", NbaseDeDatosEstudioCaso$Poblacion)
NbaseDeDatosEstudioCaso$Poblacion <- as.numeric(NbaseDeDatosEstudioCaso$Poblacion)
NbaseDeDatosEstudioCaso$Tasa <- gsub("[^0-9.]", "", NbaseDeDatosEstudioCaso$Tasa)
NbaseDeDatosEstudioCaso$Tasa <- as.numeric(NbaseDeDatosEstudioCaso$Tasa)

baseDeDatosEstudioCaso$Año <- gsub("[^0-9.]", "", baseDeDatosEstudioCaso$Año)
baseDeDatosEstudioCaso$Año <- as.numeric(baseDeDatosEstudioCaso$Año)
baseDeDatosEstudioCaso$Poblacion <- gsub("[^0-9.]", "", baseDeDatosEstudioCaso$Poblacion)
baseDeDatosEstudioCaso$Poblacion <- as.numeric(baseDeDatosEstudioCaso$Poblacion)
baseDeDatosEstudioCaso$Tasa <- gsub("[^0-9.]", "", baseDeDatosEstudioCaso$Tasa)
baseDeDatosEstudioCaso$Tasa <- as.numeric(baseDeDatosEstudioCaso$Tasa)
baseDeDatosEstudioCaso <- baseDeDatosEstudioCaso[!is.na(baseDeDatosEstudioCaso$Medida) &
                                                   !is.na(baseDeDatosEstudioCaso$TipoCancer) &
                                                   !is.na(baseDeDatosEstudioCaso$Localidad), ]
X<-baseDeDatosEstudioCaso
Y<-NbaseDeDatosEstudioCaso

head(Y)
plot(Y)
cor(Y, use="complete.obs", method="pearson")

library(Hmisc)
rcorr(as.matrix(Y), type="pearson")


completo.cancer<-lm(Casos~.,data=X) #se define la variable respuesta

step(completo.cancer, data=X, direction="backward",trace=FALSE)  #Modelo inicial para iniciar## Backward: Eliminación un paso atrás
ajuste1.cancer<-lm(Casos ~ Poblacion + Tasa, data = X)
summary(ajuste1.cancer) #se eliminan las variables mayores a 0.05

plot(ajuste1.cancer)

shapiro.test(residuals(ajuste1.cancer)) #Escogidos para verificar supuestos

library(car)
ncvTest(ajuste1.cancer, data=X) #Escogidos para verificar supuestos

library(lmtest)
dwtest(ajuste1.cancer) #Escogidos para verificar supuestos

#transformaciòn
require(MASS)
baseDeDatosEstudioCaso$Casos<-baseDeDatosEstudioCaso$Casos + 0.1

X<-baseDeDatosEstudioCaso
completo.cancerT<-lm(Casos~.,data=X)
step(completo.cancerT, data=X, direction="backward",trace=FALSE)

ajuste1.cancerT<-lm(Casos ~ Poblacion + Tasa, data = X)
summary(ajuste1.cancer)

bc<-boxcox(ajuste1.cancerT)
(lambda <- bc$x[which.max(bc$y)])

###Box-Cox en R
###Y´=(Y^lam - 1)/ lam si lam=!0
###Y´=log(Y) si lam = 0

casos.Transfor<-((X$Casos^(lambda))-1)/lambda
summary(fm1 <- lm(casos.Transfor ~ X$Casos))

plot(fm1)

shapiro.test(residuals(fm1)) #Para supuestos

library(car)
ncvTest(fm1, data=X) #Para supuestos

library(lmtest)
dwtest(fm1) #Para supuestos

# para el modelo ajustado se dice que aunmenta cantidad x si las demas constantes
# se mantienen constantes

#Nuevo estadístico
install.packages("MASS")
library(MASS)

# Selección de variables hacia atrás con cálculo de Cp
completo.cancerFinal <- stepAIC(ajuste1.cancer, direction = "backward", trace = FALSE)

# Extraer el modelo final y su Cp
completo.cancerFinal

summary(completo.cancerFinal)
