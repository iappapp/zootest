local key = 'KEY_%d'

for i=0,100000,1  do
    print(i)
    redis.call('set', string.format(key, i), math.random(100000000))
    redis.call('del', string.format(key, i))
end

return 0