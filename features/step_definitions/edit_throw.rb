Given(/^player "(.*?)" is to have their throw edited$/) do |arg1|
  @player = arg1
end

When(/^throw "(.*?)" of frame "(.*?)" for the player is changed to "(.*?)"$/) do |arg1, arg2, arg3|
  @throw = arg1
  @frame = arg2
  @score = arg3
  @output = `java -jar BowlingGame.jar debug edit_throw #{@player} #{@frame} #{@throw} #{@score} / s Player1 m a Player2 m a Player3 m a Player4 m a Player5 m a Player6 m e #{@player} #{@frame} #{@throw} #{@score}`
end

Then(/^the player's throw should reflect the desired changes as long as the input was valid$/) do
  raise (@output) unless @output[-5..-2] == 'pass'
end