import pandas as pd
import matplotlib.pyplot as plt

def plot_response_vs_load_from_csv(csv_file):
    df = pd.read_csv(csv_file)

    df_vus = df[df["metric_name"] == "vus"][["timestamp", "metric_value"]]
    df_vus.rename(columns={"metric_value": "vus"}, inplace=True)

    df_http_req_duration = df[df["metric_name"] == "http_req_duration"][["timestamp", "metric_value"]]
    df_http_req_duration.rename(columns={"metric_value": "http_req_duration"}, inplace=True)

    df_vus["timestamp"] = pd.to_datetime(df_vus["timestamp"], unit="s")
    df_http_req_duration["timestamp"] = pd.to_datetime(df_http_req_duration["timestamp"], unit="s")

    df_merged = pd.merge_asof(df_http_req_duration.sort_values("timestamp"),
                              df_vus.sort_values("timestamp"),
                              on="timestamp", direction="nearest")

    plt.figure(figsize=(10, 5))
    plt.scatter(df_merged["vus"], df_merged["http_req_duration"], alpha=0.7, label="Время отклика")
    plt.xlabel("Количество виртуальных пользователей (vus)")
    plt.ylabel("Время отклика (ms)")
    plt.title("Зависимость времени отклика от нагрузки (CSV)")
    plt.legend()
    plt.grid(True)
    plt.show()

plot_response_vs_load_from_csv("file_last_test.csv")
