# 출처:
#       GHG_DATA : UNFCCC
#       GTR_DATA : NASA
#       ASIE_DATA : NASA

import tensorflow as tf
import numpy as np
from tensorflow import keras


GHG_D = 0.0063 #
DL_Repeat = 100 #딥러닝 반복횟수 

def Put_Data_From_DB(type):
  if type == 1:
    return [0.294792255, 0.295760435, 0.294457815, 0.297446022, 0.300925525, 0.307812943, 0.317029153, 0.322177929, 0.326555368, 0.332193151, 0.341118554, 0.346573860, 0.352770093, 0.361542820, 0.370109159, 0.379932193, 0.387845226, 0.397749597, 0.402455344, 0.398034794, 0.412506367, 0.421516142, 0.429872592, 0.434870124, 0.43790063,0.481690693, 0.443452718, 0.448049306, 0.454373179, 0.454610610]
  if type == 2:
    return [0.45, 0.40, 0.22, 0.23, 0.31, 0.44, 0.32, 0.46, 0.61, 0.38, 0.39, 0.53, 0.62, 0.61, 0.53, 0.67, 0.63, 0.66, 0.54, 0.65, 0.72, 0.60, 0.64, 0.67, 0.74,0.89, 1.01, 0.92, 0.84, 0.97]
  if type == 3:
    return [0.481690693, 0.443452718, 0.448049306, 0.454373179, 0.454610610]
  if type == 4:
    return [0.89, 1.01, 0.92, 0.84, 0.97]

def Next_Data_Filling(x):
  for i in range(30):
    x.append(x_train[29] + (GHG_D * (i)))
  return

def RNN():
  model = keras.Sequential() #시작

  model.add(keras.layers.SimpleRNN(units=128, activation='tanh', input_shape=(1, 1))) #GRU알고리즘

  model.add(keras.layers.Dense(5)) #은닉층 추가
  model.add(keras.layers.Dense(1)) #은닉층 추가

  model.compile(loss='mape', optimizer='adam', metrics= ['mae']) #컴파일
  hist = model.fit(x_train, t_train, epochs=DL_Repeat, validation_data=(x_data, t_data),batch_size=1) #학습
  return model.predict(n_data) #예측

def Data_Processing(x):
  return (((t_train[29]-t_train[0]) / (prd[1]-prd[0])) * t_train[0] * x / prd[0]) - 0.2


x_train = np.array(Put_Data_From_DB(1)).reshape(30, 1)
t_train = np.array(Put_Data_From_DB(2)).reshape(30, 1)
x_data = np.array(Put_Data_From_DB(3)).reshape(5, 1)
t_data = np.array(Put_Data_From_DB(4)).reshape(5, 1)

n_data = [x_train[0],x_train[29]]
Next_Data_Filling(n_data)

n_data = np.array(n_data).reshape(32, 1) 

prd = RNN()



for j in range(30):
  print(j+2020,"년 온실가스:",n_data[j+2],j+2020,"년 지구평균온도:",Data_Processing(prd[j+2]))  








#  Memo

# 1. 북극빙하데이터
#t_train = [0.614, 0.647, 0.747, 0.64, 0.714, 0.608, 0.758, 0.669, 0.654, 0.612, 0.625, 0.673, 0.583, 0.612, 0.598, 0.55, 0.586, 0.427, 0.469, 0.526, 0.487, 0.456, 0.357, 0.521, 0.522, 0.462, 0.453, 0.482, 0.479, 0.436]
#t_data = [0.462, 0.453, 0.482, 0.479, 0.436]

# 2. 빙하면적예측
#print("2019년 대비 온실가스 110% 증가 시 북극빙하면적:", Data_Processing(prd[2]))  
#print("2019년 대비 온실가스 120% 증가 시 북극빙하면적:", Data_Processing(prd[3]))
#print("2019년 대비 온실가스 130% 증가 시 북극빙하면적:", Data_Processing(prd[4]))
#print("2019년 대비 온실가스 150% 증가 시 북극빙하면적:", Data_Processing(prd[5]))
#print("2019년 대비 온실가스 200% 증가 시 북극빙하면적:", Data_Processing(prd[6]))

#print("최근 1년 사이 북극빙하면적 감소율:", t_train[29]/t_train[28]*100,"%")
#print("최근 10년 사이 북극빙하면적 감소율:", t_train[29]/t_train[19]*100,"%")
#print("최근 20년 사이 북극빙하면적 감소율:", t_train[29]/t_train[9]*100,"%")
#print("최근 30년 사이 북극빙하면적 감소율:", t_train[29]/t_train[0]*100,"%")

# 3.Myspl

#import pymysql as ms

#db = ms.connect(host="", port="", user="", passwd="", db="", charset="")
#cursor = ms.cursor()
#sql = """
#            spl문 작성 
#      """
#cursor.execute(spl) #실행
#
#db.commit() #커밋
#db.close() #종료

