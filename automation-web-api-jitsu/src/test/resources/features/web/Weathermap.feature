Feature: Test weathermap WEB

  @web
  Scenario Outline: verify web weathermap
    Given user open openweathermap
    Given user Search the city:"<cityName>"
    And user click on city name:"<cityName>"
    Given user verify city name display success with city name:"<cityName>"
    Given user verify current date display success with time zone:"<timeZone>"
    Given user verify temperature display success
    Examples:
      | cityName        | timeZone            |
      | Los Angeles, US | America/Los_Angeles |