Given(/there is one player in the game$/) do
end

Given(/there are two players in the game$/) do
end

Given(/there are six players in the game$/) do
end


When(/^player two with name "(.*?)" is added$/) do |arg1|
  @name = arg1
  @output = `java -jar BowlingGame.jar debug add_player #{@name} 1 / s Player1 m a #{@name}`
end

Then(/^player two with the correct name should now be in the game$/) do
  raise (@output) unless @output[-5..-2] == 'pass'
end

Then(/^the player count should increase to two as long as the name is valid$/) do
  raise (@output) unless @output[-10..-7] == 'pass'
end


When(/^player three with name "(.*?)" is added$/) do |arg1|
  @name = arg1
  @output = `java -jar BowlingGame.jar debug add_player #{@name} 2 / s Player1 m a Player2 m a #{@name}`
end

Then(/^player three with the correct name should now be in the game$/) do
  raise (@output) unless @output[-5..-2] == 'pass'
end

Then(/^the player count should increase to three as long as the name is valid$/) do
  raise (@output) unless @output[-10..-7] == 'pass'
end


When(/^player seven with name "(.*?)" is added$/) do |arg1|
  @name = arg1
  @output = `java -jar BowlingGame.jar debug add_player #{@name} 6 / s Player1 m a Player1 m a Player2 m a Player3 m a Player4 m a Player5 m a Player6 m a #{@name}`
end

Then(/^player seven with the correct name should not be in the game$/) do
  raise (@output) unless @output[-5..-2] == 'pass'
end

Then(/^the player count should remain the same$/) do
  raise (@output) unless @output[-10..-7] == 'pass'
end