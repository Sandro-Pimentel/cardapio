import { useFoodDataDelete } from "../../hooks/useFoodDataDelete"
import "./card.css"

interface CardProps {
    id: number
    price: number
    title: string
    image: string
}

interface ButtonProps {
    id: number,
}

const Button = ({id}: ButtonProps) => {
    const { mutate } = useFoodDataDelete();

    const deleta = () => {
        mutate(id)
    }

    return (
        <>
            <button onClick={deleta} className="button">
                Apagar
            </button>
        </>
    )
}



export function Card({id, price, title, image}: CardProps){
    return (
        <div className="card">
            <img src={image} />
            <h2>{title}</h2>
            <p><b>Valor: </b>{price}</p>
            <Button id={id}/>
        </div>
    )
}