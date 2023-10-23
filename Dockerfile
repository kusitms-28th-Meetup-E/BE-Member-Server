FROM openjdk:17
COPY ./build/libs/memberService.jar memberService.jar
ENTRYPOINT ["java", "-jar", "memberService.jar"]