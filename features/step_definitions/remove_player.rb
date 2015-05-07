When(/^player "(.*?)" is removed$/) do |arg1|
  @player = arg1
  @output = `java -jar BowlingGame.jar debug remove_player #{@player} 1 / s Player1 m r #{@player}`
end

Then(/^playercount should not decrease$/) do
  raise (@output) unless @output[-5..-2] == 'pass'
end

When(/^player "(.*?)" of the two is removed$/) do |arg1|
  @player = arg1
  @output = `java -jar BowlingGame.jar debug remove_player #{@player} 2 / s Player1 m a Player2 m r #{@player}`
end

Then(/^playercount should decrease to one if a valid player was selected for removal$/) do
  raise (@output) unless @output[-5..-2] == 'pass'
end

When(/^player "(.*?)" of the six is removed$/) do |arg1|
  @player = arg1
  @output = `java -jar BowlingGame.jar debug remove_player #{@player} 6 / s Player1 m a Player1 m a Player2 m a Player3 m a Player4 m a Player5 m a Player6 m r #{@player}`
end

Then(/^playercount should decrease to five if a valid player was selected for removal$/) do
  raise (@output) unless @output[-5..-2] == 'pass'
end