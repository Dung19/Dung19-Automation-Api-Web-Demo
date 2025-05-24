Feature: verify api git hub SeleniumHQ

  @Api
  Scenario: verify api git hub SeleniumHQ
    And Get all repositories for SeleniumHQ
    And Count total open issues across all repositories
    And Sort repositories by update date with descending
    And Find repository with the most watchers
