/* eslint-disable @typescript-eslint/no-explicit-any */
import axios, { AxiosPromise } from "axios"
import { FoodData } from "../interface/foodData"
import { useMutation, useQueryClient } from "react-query"

const API_URL = 'http://localhost:8080'

const putData = async (data: FoodData): AxiosPromise<any> => {
    const response = await axios.put(`${API_URL}/food/${data.id}`, data)
    return response.data;
}

export function useFoodDataModify() {
    const queryClient = useQueryClient();
    const mutate = useMutation({
        mutationFn: putData,
        retry: 2,
        onSuccess: () => {
            queryClient.invalidateQueries(['food-data'])
        }
    })

    return mutate
}