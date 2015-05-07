Given(/^player "(.*?)" is to have their name edited$/) do |arg1|
  @player = arg1
end

When(/^the Edit Name option is selected$/) do
end

When(/^the name is changed by the user to "(.*?)"$/) do |arg1|
  @name = arg1
  @output = `java -jar BowlingGame.jar debug edit_name #{@player} #{@name} / s Player1 m a Player2 m p #{@player} #{@name}`
end

Then(/^the player's name should now be the new name as long as the player and name are valid$/) do
  raise (@output) unless @output[-5..-2] == 'pass'
end