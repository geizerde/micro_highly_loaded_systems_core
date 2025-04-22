import matplotlib.pyplot as plt

cores = [0.5, 1, 1.5, 2]

latency_5_95 = [67.52, 28.79, 28.21, 26.75]
latency_50_50 = [391.79, 122.64, 58.65, 70.57]
latency_95_5 = [675.1, 220.04, 166.19, 172.14]

plt.figure(figsize=(10, 6))

plt.plot(cores, latency_5_95, marker='o', label='5% POST / 95% GET')
plt.plot(cores, latency_50_50, marker='o', label='50% POST / 50% GET')
plt.plot(cores, latency_95_5, marker='o', label='95% POST / 5% GET')

plt.title('Зависимость времени отклика от количества ядер CPU')
plt.xlabel('Количество ядер')
plt.ylabel('Время отклика (ms)')
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()
