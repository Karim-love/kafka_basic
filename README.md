# 📃 Kafka 기본 코드들
```
>> kafka gradle version
kafka-clients : 2.8.1

>> 구축 kafka version
single-kafka : 2.6.0
cluster-kafka : 2.6.0
```

## ❓ kafka 동작에 대한 기본 기능
kafka의 기본 동작에 대해 pkg별로 구현한 프로젝트입니다.

## ✔️Pakage 별 정리

## 1. com.karim.kafkaBasis.kafkaConsumer
- 해당 topic에서 메세지를 구독 기능
## 2. com.karim.kafkaBasis.kafkaProducer
- 메세지를 topic에 제공 기능

## 3. com.karim.kafkaBasis.kafkaThreadBasis
- Thread로 consumer와 producer 실행
> 로직
> 1. consumer가 꺼내온 데이터를 kafkaQueue(공통 큐)로 넣는다.
> 2. kafkaQueue(공통 큐)에 데이터가 있으면
> 3. producer가 json 형식 `{"plain": "consumer로 받은 데이터"}` 로 메세지를 제공한다. 