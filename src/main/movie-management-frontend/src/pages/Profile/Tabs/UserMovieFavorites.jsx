import React, {useState, useEffect} from 'react';
import FavoritesService from '../../../services/FavoritesService';
import FavoriteCardJsx from './FavoriteCard';
import { AiOutlineLoading3Quarters } from "react-icons/ai";
import AuthService from '../../../services/AuthService';
import './Tabs.css';

function UserMovieFavoritesJsx(){
    const currentUser = AuthService.getCurrentUser();
    const [favorites, setFavorites] = useState();
    const [loading, setLoading] = useState(true);
    useEffect(()=>{
        FavoritesService.getUserFavorites(currentUser.id).then(res => {
            setFavorites([...res.data]);
            setLoading(false);
        }).catch(error => {console.log(error);})
    }, [])
    const removeFromFavorites = (favId) => {
        FavoritesService.deleteUserFavorite(favId, currentUser.id).then(res => {
            setFavorites([...res.data]);
        }).catch(error => {console.log(error);})
    }
    const renderFavorites = () => {
        if (favorites.length !== undefined){
            return (
                <>
                    {favorites.map(function(fav, i){
                        return (
                            <>
                                <div key={i} className="activity">
                                    <FavoriteCardJsx {...fav} removeFromFavorites={removeFromFavorites}/>
                                </div>
                            </>
                        )
                    })}
                </>
            )
        }else return (<></>);
    }
    return (
        <>
            {loading?(<><AiOutlineLoading3Quarters className="loading"/></>)
            :(<>{favorites.length>0? (renderFavorites()):(<h3>No Favorites Found</h3>)}</>)}
        </>
    )
}

export default UserMovieFavoritesJsx