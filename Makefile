default: compile

clean:
	./gradlew clean

compile:
	./gradlew build -xtest

build: compile

deploy: compile
	./gradlew deploy

versioncheck:
	./gradlew dependencyUpdates

depends:
	./gradlew dependencies