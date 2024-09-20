@register
  Feature: Register new account
    @registerSuccess
      Scenario Outline: Sign up with new user
      Given User click button link field "Register"
      When User select dropdown list field "title" with value "<Title>"
      And User fill textbox field "firstname" with value "<Firstname>"
      And User fill textbox field "lastname" with value "<Surname>"
      And User fill textbox field "phone" with value "<Phone>"
      And User select dropdown list field "year" with value "<Year>"
      And User select dropdown list field "month" with value "<Month>"
      And User select dropdown list field "date" with value "<Day>"
      And User select radio button with value "<Radio>"
      And User select dropdown list field "licenceperiod" with value "<License Period>"
      And User select dropdown list field "occupation" with value "<Occupation>"
      And User fill textbox field "street" with value "<Street>"
      And User fill textbox field "city" with value "<City>"
      And User fill textbox field "county" with value "<Country>"
      And User fill textbox field "post_code" with value "<Postcode>"
      And User fill textbox field "email" with value "<Email>"
      And User fill textbox field "password" with value "<Password>"
      And User fill textbox field "c_password" with value "<Confirm Password>"
      And User click button input field "Create"
      #Login
      Then User fill textbox field "email" with value "<Email>"
      And User fill textbox field "password" with value "<Password>"
      And User click button input field "Log in"
      #Login success and navigate to Home
      Then User verify login sucessfully

        Examples:
          | Title | Firstname | Surname | Phone     | Year | Month | Day | Radio | License Period | Occupation | Street        | City | Country | Postcode | Email | Password | Confirm Password |
          | Mrs   | Dung      | Dao     | 029384324 | 1992 | March | 17  | Full  | 1              | Academic   | Duong Ba Trac | HCM  | VN      | 100000   | dung  | admin    | admin            |
