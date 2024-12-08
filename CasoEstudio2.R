install.packages("readxl")
library(readxl)

baseDeDatosEstudioCaso=read_excel('C:\\Users\\abell\\Desktop\\baseDeDatosEstudioCaso.xlsx')

# Casos totales de morbilidad y mortalidad
suma_total <- sum(baseDeDatosEstudioCaso$Casos, na.rm = TRUE)

# Filtrar las filas donde la medida es "Morbilidad"
morbilidad_data <- baseDeDatosEstudioCaso[baseDeDatosEstudioCaso$Medida == "Morbilidad", ]

# Seleccionar las columnas deseadas
morbilidad_data <- morbilidad_data[, c("Medida", "TipoCancer", "Casos")]

# Filtrar por cancer de mama y cuello uterino
casosCancerMama<-morbilidad_data[morbilidad_data$TipoCancer == "Mama", ]
casosCancerUterino<-morbilidad_data[morbilidad_data$TipoCancer == "C. Uterino", ]

# Casos totales de morbilidad por cancer de mama y cuello uterino
suma_mama <- sum(casosCancerMama$Casos, na.rm = TRUE)
suma_uterino <- sum(casosCancerUterino$Casos, na.rm = TRUE)

#sigue unilateral a derecha

x1<-suma_uterino
x2<-suma_mama
n1<-suma_total
n2<-suma_total
p1<-x1/n1
p2<-x2/n2
alfa<-0.01
decision<-qnorm(1-alfa)

# Verificamos normalidad, los datos deben ser mayores a 10
normal11<-n1*p1
normal12<-n1*(1-p1)
normal21<-n2*p2
normal22<-n2*(1-p2)

pto<-(x1+x2)/(n1+n2)

Zc<-((p1-p2)/sqrt((pto*(1-pto))*((1/n1)+(1/n2))))
p<-pnorm(Zc, lower.tail = FALSE)
#como P es menor que alfa, se puede rechazar la afirm.
#nula con nivel de significancia de 1%



# Generar la tabla de contingencia
tabla_contingencia <- table(baseDeDatosEstudioCaso$Medida,
                            baseDeDatosEstudioCaso$TipoCancer)

# Mostrar la tabla de contingencia
print(tabla_contingencia)

# Convertir la tabla de contingencia a un vector para poder graficar
valores_frecuencia <- as.numeric(tabla_contingencia)

uni_izq <- qnorm(alfa)
uni_der <- qnorm(1-alfa)

#Rechazo H0 si es menor a -2.3263 o mayor a 2.3263
