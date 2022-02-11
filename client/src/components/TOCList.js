import React, { Component } from "react";
import {Link} from 'react-router-dom';
class TOCList extends Component {
    shouldComponentUpdate(newProps, newState){
        if(this.props.data === newProps.data) return false;
        return true;
    }

    render() {
        var lists = [];
        var data = this.props.data;
        var i = 0;
        while (i < data.length) {
            lists.push(<li key={data[i].id}>
                <a
                    href={"/pocket/" + data[i].title}
                    data-id={data[i].id}
                    onClick={function (e) {
                        e.preventDefault();
                        this.props.onChangePage(e.target.dataset.id);
                    }.bind(this)}
                >{data[i].title}</a></li>)
            i++;
        }

        return (
            <nav>
                <ul>
                    {lists}
                </ul>
            </nav>
        );
    }
}

export default TOCList;